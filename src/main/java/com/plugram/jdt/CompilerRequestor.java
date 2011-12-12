package com.plugram.jdt;

import org.eclipse.jdt.core.compiler.IProblem;
import org.eclipse.jdt.internal.compiler.CompilationResult;
import org.eclipse.jdt.internal.compiler.ICompilerRequestor;
import org.eclipse.jdt.internal.compiler.ClassFile;


public class CompilerRequestor implements ICompilerRequestor {

	@Override
	public void acceptResult(CompilationResult result) {
		// エラーがあった場合
        if (result.hasErrors()) {
            for (IProblem problem: result.getErrors()) {
                String className = new String(problem.getOriginatingFileName()).replace("/", ".");
                className = className.substring(0, className.length() - 5);
                String message = problem.getMessage();
                if (problem.getID() == IProblem.CannotImportPackage) {
                    message = problem.getArguments()[0] + " cannot be resolved";
                }
                throw new UnexpectedException(message);
            }
        }
        // コンパイルされたクラスファイルを取得する
        ClassFile[] clazzFiles = result.getClassFiles();
        for (int i = 0; i < clazzFiles.length; i++) {
            final ClassFile clazzFile = clazzFiles[i];
            final char[][] compoundName = clazzFile.getCompoundName();
            final StringBuffer clazzName = new StringBuffer();
            for (int j = 0; j < compoundName.length; j++) {
                if (j != 0) {
                    clazzName.append('.');
                }
                clazzName.append(compoundName[j]);
            }
            // コンパイルされたクラスを保存する
            AppClasses.storeCompiledClass(clazzName.toString(), clazzFile.getBytes());
        }
	}

}
