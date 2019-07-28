package com.example.commandline;

import org.springframework.boot.ExitCodeGenerator;
import org.springframework.stereotype.Component;

@Component
public class ExitCodeSet implements ExitCodeGenerator {
    @Override
    public int getExitCode() {
        return 8;
    }
}
