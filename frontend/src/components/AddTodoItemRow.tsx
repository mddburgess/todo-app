import TodoItemForm from "./TodoItemForm";
import {useCreateTodoItemMutation} from "../api/todoApi";

const AddTodoItemRow = () => {
    const [createTodoItem] = useCreateTodoItemMutation();
    const initialState = {
        body: {
            summary: ""
        }
    }

    return (
        <li>
            <TodoItemForm initialState={initialState} onSave={createTodoItem}/>
        </li>
    )
}

export default AddTodoItemRow;
