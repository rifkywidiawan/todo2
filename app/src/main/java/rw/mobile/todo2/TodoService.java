package rw.mobile.todo2;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface TodoService {
    @GET("todo.php")
    Call<List<TodoItem>> getTodoItems();
}
