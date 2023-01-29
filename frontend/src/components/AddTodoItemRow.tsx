import TodoItemForm from "./TodoItemForm";
import {useCreateTodoItemMutation} from "../api/todoApi";

const AddTodoItemRow = () => {
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
        <li>
            <TodoItemForm initialState={initialState} onSave={createTodoItem}/>
        </li>
    )
}

export default AddTodoItemRow;
