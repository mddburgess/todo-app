import TodoItemForm from "./TodoItemForm";
import {useCreateTodoItemMutation} from "../api/todoApi";
import ListGroupItem from "react-bootstrap/ListGroupItem";

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
        <ListGroupItem>
            <TodoItemForm initialState={initialState} onSave={createTodoItem}/>
        </ListGroupItem>
    )
}

export default AddTodoItemRow;
