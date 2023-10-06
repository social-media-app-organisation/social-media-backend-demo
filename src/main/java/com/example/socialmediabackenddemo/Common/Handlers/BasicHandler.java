package com.example.socialmediabackenddemo.Common.Handlers;

public abstract class BasicHandler<Command,Response> {
    public abstract Response handle(Command c) throws Exception;
}
