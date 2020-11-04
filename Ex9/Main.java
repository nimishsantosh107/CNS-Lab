import java.util.*;
class Main {
    public static void main(String[] args) throws Exception {
        int choice;
        Scanner sc = new Scanner(System.in);
        DSS dss = new DSS();
        do {
            System.out.print("1. Create Digital Signature\n2. Verify Digital Signature\n3. Exit\n");
            choice = sc.nextInt();
            sc.nextLine();
            if (choice == 1) {
                System.out.print("ENTER KEY: ");
                String plainText = sc.nextLine();
                String signature = dss.createDigitalSignature(plainText);
                System.out.println("DIGITAL SINGATURE: " + signature);
            } else if (choice == 2) {
                System.out.print("ENTERY KEY: ");
                String verificationText = sc.nextLine();
                boolean verified = dss.verifyDigitalSignature(verificationText);
                if (verified) System.out.println("\nSIGNATURE VERIFIED\n");
                else System.out.println("\nSIGNATURE ERROR\n");
            }
        } while (choice != 3);
    }
}