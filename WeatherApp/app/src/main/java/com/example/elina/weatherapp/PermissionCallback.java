package com.example.elina.weatherapp;

public interface PermissionCallback {
    void permissionRec(PermissionChecker.RuntimePermissions permission);
    void permissionNotRec(PermissionChecker.RuntimePermissions permission);
}
