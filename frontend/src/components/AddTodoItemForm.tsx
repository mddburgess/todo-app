import TodoItemForm from "./TodoItemForm";
import {useCreateTodoItemMutation} from "../api/todoApi";

const AddTodoItemForm = () => {
    const [createTodoItem] = useCreateTodoItemMutation();
    const initialState = {
        todoItem: {
            id: undefined,
            summary: "",
            created_at: undefined,
            updated_at: undefined
        }
    }

    return (
        <TodoItemForm initialState={initialState} onSubmit={createTodoItem}/>
    )
}

export default AddTodoItemForm;
