package cn.vfc.test;

public class BuildTest {
    private String mBoard;
    private String mDisplay;
    private String mOs;

    private BuildTest(Builder builder) {
        setmBoard(builder.mBoard);
        setmDisplay(builder.mDisplay);
        setmOs(builder.mOs);
    }

    public String getmBoard() {
        return mBoard;
    }

    public void setmBoard(String mBoard) {
        this.mBoard = mBoard;
    }

    public String getmDisplay() {
        return mDisplay;
    }

    public void setmDisplay(String mDisplay) {
        this.mDisplay = mDisplay;
    }

    public String getmOs() {
        return mOs;
    }

    public void setmOs(String mOs) {
        this.mOs = mOs;
    }


    public static final class Builder {
        private String mBoard;
        private String mDisplay;
        private String mOs;

        public Builder() {
        }

        public Builder mBoard(String val) {
            mBoard = val;
            return this;
        }

        public Builder mDisplay(String val) {
            mDisplay = val;
            return this;
        }

        public Builder mOs(String val) {
            mOs = val;
            return this;
        }

        public BuildTest build() {
            return new BuildTest(this);
        }
    }

    @Override
    public String toString() {
        return "BuildTest{" +
                "mBoard='" + mBoard + '\'' +
                ", mDisplay='" + mDisplay + '\'' +
                ", mOs='" + mOs + '\'' +
                '}';
    }

    public static void main(String[] args) {
        BuildTest build = new BuildTest.Builder()
                .mBoard("111")
                .mDisplay("222")
                .mOs("222")
                .build();
        System.out.println(build.toString());
    }
}

