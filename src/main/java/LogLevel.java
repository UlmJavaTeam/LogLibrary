public enum LogLevel {
    DEBUG (1), INFO (2), WARNING (3), ERROR (4);

    int level;
    private LogLevel (int level) {
         this.level = level;
    }
    public int getLevelValue() {
        return level;
    }
}
