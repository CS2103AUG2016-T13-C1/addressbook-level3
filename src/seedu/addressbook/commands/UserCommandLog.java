package seedu.addressbook.commands;

import java.util.ArrayList;

public class UserCommandLog {
    private ArrayList<String> userCommandLog;
    private int currentCommandIndex;
    
    public UserCommandLog(){
    	userCommandLog = new ArrayList<String>();
    	currentCommandIndex = 0;
    }
    
    public void addCommandToUserLog(String userCommand){
    	userCommandLog.add(userCommand);
    	currentCommandIndex = userCommandLog.size();
    }
    
    public String getPreviousCommand(){
    	String previousCommand = "";
    	if(currentCommandIndex - 1 >= 0){
    		currentCommandIndex -= 1;
    		previousCommand = userCommandLog.get(currentCommandIndex);
    	}
    	else{
    		System.out.println("reached end of history");
    		previousCommand = userCommandLog.get(0);
    	}
    	return previousCommand;
    }
	
    public String getNextCommand(){
    	String nextCommand = "";

    	if(currentCommandIndex + 1 < userCommandLog.size()){
    		currentCommandIndex += 1;
    		nextCommand = userCommandLog.get(currentCommandIndex);
    	}
    	else{
    		nextCommand = "";
    		currentCommandIndex = userCommandLog.size();
    		System.out.println("reached most latest command");
    	}
    	return nextCommand;
    }
}
