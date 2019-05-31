package DawidKlosowski.example.ToDoProject.minimaltodo.AddToDo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import DawidKlosowski.example.ToDoProject.minimaltodo.AppDefault.AppDefaultActivity;
import com.example.Project.minimaltodo.R;

public class AddToDoActivity extends AppDefaultActivity {
    @SuppressWarnings("deprecation")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int contentViewLayoutRes() {
        return R.layout.activity_add_to_do;
    }

    @NonNull
    @Override
    protected Fragment createInitialFragment() {
        return AddToDoFragment.newInstance();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

}

