package rw.mobile.todo2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://mgm.ub.ac.id/todo.php/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    private TodoService todoService = retrofit.create(TodoService.class);

    private TodoAdapter adapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new TodoAdapter();
        recyclerView.setAdapter(adapter);

        fetchTodoItems();
    }

    private void fetchTodoItems() {
        todoService.getTodoItems().enqueue(new Callback<List<TodoItem>>() {
            @Override
            public void onResponse(Call<List<TodoItem>> call, Response<List<TodoItem>> response) {
                if (response.isSuccessful()) {
                    adapter.setData(response.body());
                } else {
                    Toast.makeText(MainActivity.this, "Error: " + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<TodoItem>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}