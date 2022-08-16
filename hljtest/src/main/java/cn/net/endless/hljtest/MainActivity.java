package cn.net.endless.hljtest;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.flyingpigeon.library.Pigeon;


public class MainActivity extends Activity {
    private Pigeon flyPigeon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        flyPigeon = Pigeon.newBuilder(this).setAuthority("cn.net.hylink.span").build();
    }

    public void test(View view) {
        flyPigeon.route("/snap/push", "face_record", "{\"type\":1,\"time\":\"2019-10-10 19:00:00\",\"camid\": 1, \"ip\": \"192.168.1.60\",\"image\":{\"big\":{\"url\":\"https://pics2.baidu.com/feed/6a63f6246b600c33450299f874e4f605d9f9a13e.jpeg?token=90be28f0b70460ea550267c690476377\",\"b64\":\"\"},\"small\":[{\"url\":\"https://pics2.baidu.com/feed/6a63f6246b600c33450299f874e4f605d9f9a13e.jpeg?token=90be28f0b70460ea550267c690476377\",\"b64\":\"\"}]}}")
                .resquestLarge()
                .fly();

    }
}
