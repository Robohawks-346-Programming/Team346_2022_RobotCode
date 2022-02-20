package frc.robot.util;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class LaserBreak {
    DigitalInput dio;

    public LaserBreak(int port) {
        dio = new DigitalInput(port);
    }

    public boolean returnBreak() {
        return dio.get();
    }

    public void pushData() {
        SmartDashboard.putBoolean("Laser break unobstructed: ", dio.get());
    }
}
