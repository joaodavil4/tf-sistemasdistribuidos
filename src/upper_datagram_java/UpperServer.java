package upper_datagram_java;

import java.io.*;

public class UpperServer {
  public static void main(String[] args) throws IOException {
    new UpperServerThread().start();
  }
}
