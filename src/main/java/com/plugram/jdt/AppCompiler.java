package com.plugram.jdt;

import java.util.HashMap;

import java.util.Locale;
import java.util.Map;

import org.eclipse.jdt.internal.compiler.CompilationResult;
import org.eclipse.jdt.internal.compiler.Compiler;
import org.eclipse.jdt.internal.compiler.DefaultErrorHandlingPolicies;
import org.eclipse.jdt.internal.compiler.ICompilerRequestor;
import org.eclipse.jdt.internal.compiler.IErrorHandlingPolicy;
import org.eclipse.jdt.internal.compiler.IProblemFactory;
import org.eclipse.jdt.internal.compiler.ast.CompilationUnitDeclaration;
import org.eclipse.jdt.internal.compiler.env.ICompilationUnit;
import org.eclipse.jdt.internal.compiler.env.INameEnvironment;
import org.eclipse.jdt.internal.compiler.impl.CompilerOptions;
import org.eclipse.jdt.internal.compiler.problem.DefaultProblemFactory;


/**
 * Java 1.6でコンパイルします。
 * 
 * @author tango
 */
public class AppCompiler {

	/**
	 * コンパイルします。
	 * @param classNames
	 */
	@SuppressWarnings("deprecation")
	public void compile(String[] classNames) {
        
        ICompilationUnit[] compilationUnits = new CompilationUnit[classNames.length];
        for (int i = 0; i < classNames.length; i++) {
            compilationUnits[i] = new CompilationUnit(classNames[i]);
        }
        IErrorHandlingPolicy policy = DefaultErrorHandlingPolicies.exitOnFirstError();
        IProblemFactory problemFactory = new DefaultProblemFactory(Locale.JAPANESE);
        
        INameEnvironment nameEnvironment = new NameEnvironment();
        ICompilerRequestor compilerRequestor = new CompilerRequestor();
        
		Compiler jdtCompiler = new Compiler(nameEnvironment, policy, settings(), compilerRequestor, problemFactory) {
            @Override
            protected void handleInternalException(Throwable e, CompilationUnitDeclaration ud, CompilationResult result) {
            }
        };

        jdtCompiler.compile(compilationUnits);
        
	}

	private Map<String, String> settings() {
		Map<String, String> settings = new HashMap<String, String>();
		settings.put(CompilerOptions.OPTION_ReportMissingSerialVersion, CompilerOptions.IGNORE);
        settings.put(CompilerOptions.OPTION_LineNumberAttribute, CompilerOptions.GENERATE);
        settings.put(CompilerOptions.OPTION_SourceFileAttribute, CompilerOptions.GENERATE);
        settings.put(CompilerOptions.OPTION_ReportDeprecation, CompilerOptions.IGNORE);
        settings.put(CompilerOptions.OPTION_ReportUnusedImport, CompilerOptions.IGNORE);
        settings.put(CompilerOptions.OPTION_Encoding, "UTF-8");
        settings.put(CompilerOptions.OPTION_LocalVariableAttribute, CompilerOptions.GENERATE);
        settings.put(CompilerOptions.OPTION_Source, CompilerOptions.VERSION_1_6);
        settings.put(CompilerOptions.OPTION_TargetPlatform, CompilerOptions.VERSION_1_6);
        settings.put(CompilerOptions.OPTION_PreserveUnusedLocal, CompilerOptions.PRESERVE);
        settings.put(CompilerOptions.OPTION_Compliance, CompilerOptions.VERSION_1_6);
		return settings;
	}
}
