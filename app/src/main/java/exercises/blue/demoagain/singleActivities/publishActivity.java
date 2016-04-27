package exercises.blue.demoagain.singleActivities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ToggleButton;

import exercises.blue.demoagain.R;

public class publishActivity extends AppCompatActivity {



    EditText title,content;
    ToggleButton tgbutton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_layout);
        getSupportActionBar().hide();
        title = (EditText) findViewById(R.id.add_item);
        content= (EditText) findViewById(R.id.add_content);
        tgbutton =(ToggleButton) findViewById(R.id.is_hot);

    }

    public void publish_click(View view) {
        int id = view.getId();
        if(id==R.id.add_pub)
        {
//            friendsDatum user = new friendsDatum();
//            user.setTitle(title.getText().toString());
//            user.setContent(content.getText().toString());
//            user.setCategory(tgbutton.isChecked()?1:0);
            //TODO: 将内容加入到返回中

            Intent intentToRtn = new Intent();
            Bundle bundle = new Bundle();
            bundle.putString("title",title.getText().toString());
            bundle.putString("content",content.getText().toString());
            bundle.putInt("category",tgbutton.isChecked()?1:0);
            intentToRtn.putExtra("user",bundle);
            //intentToRtn.putExtras()
            setResult(213,intentToRtn);
        }
        else if( id == R.id.add_cancel){
            setResult(444,null);
        }
        finish();
    }
}
