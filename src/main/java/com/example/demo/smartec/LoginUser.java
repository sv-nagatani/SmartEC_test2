package com.example.demo.smartec;

import java.util.List;

public record LoginUser(String name, String dspName, String password, List<String> roleList) {
}