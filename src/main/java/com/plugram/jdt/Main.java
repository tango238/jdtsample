package com.plugram.jdt;


/**
 * Hello world!
 *
 */
public class Main 
{
	public static final String SOURCE_DIR = "/Users/plugram/Documents/workspaces/github/jdtsample/src/main/java/";
	
    public static void main( String[] args )
    {
        new Main().run();
    }
    
    public void run(){
    	AppCompiler compiler = new AppCompiler();
    	String[] classes = new String[]{"com.plugram.sample.Hello"};
    	compiler.compile(classes);
    	System.out.println("Compiled.");
    }
    
}
