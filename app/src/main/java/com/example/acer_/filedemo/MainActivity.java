package com.example.acer_.filedemo;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private TextView tv1;
    private File fPhonedirectory;
    private File fExternalStoragePublicDirectory;
    private File fExternalStorageDirectory;
    private File fDataStorage;
    private File fDownloadCacheDirecotory;
    private File fRootDirectory;
    private String name,path;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv1 = (TextView) findViewById(R.id.result);
        fPhonedirectory = this.getFilesDir();
        fExternalStoragePublicDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC);
        fExternalStorageDirectory = Environment.getExternalStorageDirectory();
        fDataStorage = Environment.getDataDirectory();
        fDownloadCacheDirecotory = Environment.getDownloadCacheDirectory();
        fRootDirectory = Environment.getRootDirectory();

        if (Environment.getExternalStorageDirectory().equals(Environment.MEDIA_REMOVED)){
            Button btn = (Button) findViewById(R.id.externalStorageDicetory);
            btn.setEnabled(false);
        }
    }
    public void phoneDicetory(View v) throws IOException {
      path = fPhonedirectory.getPath();
        try {
            FileOutputStream fos = openFileOutput("text.txt",MODE_PRIVATE);
            fos.write("hello".getBytes());
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        listFile(path);
    }
    public void externalStoragePublicDicetory(View v){
        path = fExternalStoragePublicDirectory.getAbsolutePath();
        listFile(path);
    }
    public void externalStorageDicetory(View v){
         path = fExternalStorageDirectory.getAbsolutePath();
        listFile(path);

    }
    public void dataStorage(View v){
        path = fDataStorage.getAbsolutePath();
        listFile(path);

    }
    public void downloadCacheDirectory(View v){
        path = fDownloadCacheDirecotory.getAbsolutePath();
        listFile(path);
    }
    public void rootDirectory(View v){
        path = fRootDirectory.getAbsolutePath();
        listFile(path);
    }
    private boolean listFile(String path) {
        name = "路径"+path+"\n文件清单:\n";
        File file = new File(path);
        if(file.listFiles()!=null&&file.listFiles().length>0){
            for (File f:file.listFiles()){
                path = f.getAbsolutePath();
                name = name + f.getName() + "\n";
            }
        }
        tv1.setText(name);
        return true;
    }
}
