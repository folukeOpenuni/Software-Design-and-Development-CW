/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdd.cw;

/**
 *The UnknownColumnException class 
 * @author faagbede
 */
public class UnknownColumnException extends Exception{
    
    /**
     * The UnknownColumnException is the parameterless constructor for the UnknownColumnException class
     */
    public UnknownColumnException(){
        
    }
    
    /**
     * This constructor display the message of the error that occur 
     * @param message the massage of the error
     */
    public UnknownColumnException(String message){
        super(message);
    }
    
    /**
     * This constructor throws the cause of the error 
     * @param cause the cause of the error 
     */
    public UnknownColumnException(Throwable cause){
        super(cause);
    }
    
    /**
     * This constructor shows both the massage and the cause of the error 
     * @param message the massage of the error 
     * @param cause the cause of the error
     */
    public UnknownColumnException(String message, Throwable cause){
        super(message, cause);
    }
}
