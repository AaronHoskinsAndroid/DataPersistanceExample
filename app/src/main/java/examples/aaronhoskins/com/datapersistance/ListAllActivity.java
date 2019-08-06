package examples.aaronhoskins.com.datapersistance;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class ListAllActivity extends AppCompatActivity {
    RecyclerView rvCarList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_all);
        rvCarList = findViewById(R.id.recyclerView);
        CarDatabaseHelper carDatabaseHelper = new CarDatabaseHelper(this);
        CarsRecyclerViewAdapter carsRecyclerViewAdapter = new CarsRecyclerViewAdapter(carDatabaseHelper.getAllCars());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvCarList.setLayoutManager(layoutManager);
        rvCarList.setAdapter(carsRecyclerViewAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @SuppressWarnings("unused")
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void messageEvent(MessageEvent me){
        Toast.makeText(this, me.getMessage(), Toast.LENGTH_LONG).show();


    }
}
