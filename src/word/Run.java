package word;

public class Run {
    public static void main(String[] args) {
        Program program = new Program();
        program.createMatrix("qyurtyuiopasdfgh");
        program.printMatrix();
        program.findWord("yuagh");
    }
}
