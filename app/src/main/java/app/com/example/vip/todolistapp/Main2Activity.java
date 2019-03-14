package app.com.example.vip.todolistapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

public class Main2Activity extends AppCompatActivity {
    EditText editText;
    int noteId;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        editText = findViewById(R.id.editText);
        intent = getIntent();
        noteId = intent.getIntExtra("noteId" , -1);
        if (noteId != -1){
            editText.setText(MainActivity.toDoList.get(noteId));
        }else {
            //add empty item that will update text by method onTextChanged if we write anything in it and give it id as last item.
            MainActivity.toDoList.add("");
            noteId = MainActivity.toDoList.size() - 1;
        }
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                MainActivity.toDoList.set(noteId , charSequence.toString());
                MainActivity.arrayAdapter.notifyDataSetChanged();

                //save to shared prefs using TinyDB
                TinyDB tinyDB = new TinyDB(Main2Activity.this);
                tinyDB.putListString("keyNotesList" , MainActivity.toDoList);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });



    }
}
