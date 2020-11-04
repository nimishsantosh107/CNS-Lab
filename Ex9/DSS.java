import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
public class DSS {
    public byte[] signature;
    public PublicKey pubkey;
    public String createDigitalSignature(String plainText) throws Exception {
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("DSA");
        keyPairGen.initialize(2048);
        KeyPair pair = keyPairGen.generateKeyPair();
        PrivateKey privKey = pair.getPrivate();
        pubkey = pair.getPublic();
        Signature sign = Signature.getInstance("SHA256withDSA");
        sign.initSign(privKey);
        byte[] bytes = plainText.getBytes();
        sign.update(bytes);
        signature = sign.sign();
        return new String(signature, "UTF8");
    }
    public boolean verifyDigitalSignature(String verificationText) throws Exception {
        Signature sign = Signature.getInstance("SHA256withDSA");
        byte[] bytes = verificationText.getBytes();
        sign.initVerify(pubkey);
        sign.update(bytes);
        boolean verified = sign.verify(signature);
        return verified;
    }
}