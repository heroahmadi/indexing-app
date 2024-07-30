package org.indexing;

import java.io.FileWriter;
import java.io.IOException;

public class TestUtil {

    public static void main(String[] args) throws IOException {
        String sampleText = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.\n" +
                "1234567890 !@#$%^&*()_+-=[]{}|;:',.<>/?`~\n\n" +
                "Sed ut perspiciatis unde omnis iste natus   erro r sit voluptatem accusantium Doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit.\n" +
                "Quis autem vel eum iure reprehenderit qui in ea voluptate velit Esse quam nihil molestiae consequatur, vel illum qui dolorem eum fugiat quo voluptas nulla pariatur. At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium voluptatum deleniti atque corrupti quos dolores et quas molestias excepturi sint occaecati cupiditate non provident.\n" +
                "1234567890 !@#$%^&*()_+-=[]{}|;:',.<>/?`~\n" +
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.\n" +
                "Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt molliD Z anim id est laborum. Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo.\n" +
                "1234567890 !@#$%^&*()_+-=[]{}|;:',.<>/?`~\n";

        String fileName = "test_file2.txt";
        int targetSizeMB = 2;
        long targetSizeBytes = targetSizeMB * 1024 * 1024;

        try (FileWriter writer = new FileWriter(fileName)) {
            long bytesWritten = 0;
            while (bytesWritten < targetSizeBytes) {
                writer.write(sampleText);
                bytesWritten += sampleText.getBytes().length;
            }
            System.out.println("File created successfully: " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
