package somepackage.entities;

/**
 * Created by Andrity Zhuk on 1/26/2017.
 */
public class CommandExecutionResult
{
    String caption;
    String type;
    String executionValue;
    boolean wasPassed;

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getExecutionValue() {
        return executionValue;
    }

    public void setExecutionValue(String value) {
        this.executionValue = value;
    }

    public boolean isWasPassed() {
        return wasPassed;
    }

    public void setWasPassed(boolean wasPassed) {
        this.wasPassed = wasPassed;
    }

    public CommandExecutionResult(String caption, String value, boolean wasPassed) {
        this.caption = caption;
//        this.type = type;
        this.executionValue = value;
        this.wasPassed = wasPassed;
    }


    @Override
    public String toString() {
        return "CommandExecutionResult{" +
                "caption='" + caption + '\'' +
                ", type=" + type +
                ", value='" + executionValue + '\'' +
                ", wasPassed=" + wasPassed +
                '}';
    }
}
