package view;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class NQueensButtonListener extends MouseAdapter 
{
	 private Object myController;
	 private Method myMethod;
	 private Object myArgs;
		
	 public NQueensButtonListener(Object controller, Method method, Object params) 
	 {
		myController = controller;
		myMethod = method;
		myArgs = params;
	 }
		
	 public void mouseReleased(MouseEvent event)
	 {
		try
		{
		   myMethod.invoke(myController, myArgs);
		}
		catch(IllegalArgumentException e)
		{
		   e.printStackTrace();
		}
		catch(IllegalAccessException e)
		{
		   e.printStackTrace();
		}
		catch(InvocationTargetException e)
		{
		   Throwable cause = e.getCause();
		   System.out.println("error in button listener: " + cause.getMessage());
		   e.printStackTrace();
		}
	 }
}