import com.leyou.common.JwtUtils;
import com.leyou.common.RsaUtils;
import com.leyou.common.UserInfo;
import org.junit.Before;
import org.junit.Test;

import java.security.PrivateKey;
import java.security.PublicKey;

public class JwtTest {

    private static final String pubKeyPath = "C:\\tmp\\rsa\\rsa.pub";

    private static final String priKeyPath = "C:\\tmp\\rsa\\rsa.pri";

    private PublicKey publicKey;

    private PrivateKey privateKey;

    @Test
    public void testRsa() throws Exception {
        RsaUtils.generateKey(pubKeyPath, priKeyPath, "234");
    }

    @Before
    public void testGetRsa() throws Exception {
        this.publicKey = RsaUtils.getPublicKey(pubKeyPath);
        this.privateKey = RsaUtils.getPrivateKey(priKeyPath);
    }

    @Test
    public void testGenerateToken() throws Exception {
        // 生成token
        String token = JwtUtils.generateToken(new UserInfo(20L, "jack"), privateKey, 5);
        System.out.println("token = " + token);
    }

    @Test
    public void testParseToken() throws Exception {
        String token = "eyJhbGciOiJSUzI1NiJ9.eyJpZCI6MjAsInVzZXJuYW1lIjoiamFjayIsImV4cCI6MTU5MjMxMTcwOX0.WPu_bQbGYV23_8FABgEV4T8JKJe2UqMtA8jZEO23wTSJhFqL6ks44Vx4cj9fTiDkQ4S-tPJUlHnnDrOmeXNXDnRGcOaNRhYx9MSQ_e_HcR-1yeLSa9WxoFDiNfOI5f9nNB59DWUenKYSDS6EQ8IrlIWBbaKeeJfPfr1Fb8iYb8s";
        // 解析token
        UserInfo user = JwtUtils.getInfoFromToken(token, publicKey);
        System.out.println("id: " + user.getId());
        System.out.println("userName: " + user.getUsername());
    }
}