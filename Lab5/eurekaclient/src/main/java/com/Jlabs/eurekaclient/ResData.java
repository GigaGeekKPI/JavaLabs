package com.Jlabs.eurekaclient;

public class ResData<T> {
    private final T data;
    private final String instanceId;

    public ResData(T data, String instanceId) {
        this.data = data;
        this.instanceId = instanceId;
    }

    public String getInstanceId() {
        return instanceId;
    }

    public T getData() {
        return data;
    }
}