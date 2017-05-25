package com.group4.shared.Model;

import java.io.Serializable;

/**
 * Created by tyler on 5/12/17.
 */

public class Results
{
    boolean success;
    String data;
    String errorInfo;
    CommandList commandList;


    public Results(boolean success, String data, String errorInfo, CommandList commandList)
    {
        this.success = success;
        this.data = data;
        this.errorInfo = errorInfo;
        this.commandList = commandList;
    }

    public boolean isSuccess()
    {
        return success;
    }

    public void setSuccess(boolean success)
    {
        this.success = success;
    }

    public String getData()
    {
        return data;
    }

    public void setData(String data)
    {
        this.data = data;
    }

    public String getErrorInfo()
    {
        return errorInfo;
    }

    public void setErrorInfo(String errorInfo)
    {
        this.errorInfo = errorInfo;
    }

    public CommandList getCommandList()
    {
        return commandList;
    }

    public void setCommandList(CommandList commandList)
    {
        this.commandList = commandList;
    }
}
