import NameTool.src.main.java.de.hse.swb.swt.nametool.NameTool;

public class Main {
    public static void main(String[] args) {
        String nameToolResult = NameTool.newName("Menschens", "Kindersmustermann");
        System.out.println(String.format("Text: %s - Length: %d", nameToolResult, nameToolResult.length()));
    }
}
