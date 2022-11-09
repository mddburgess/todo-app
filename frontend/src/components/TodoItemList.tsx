import {useListTodoItemsQuery} from "../api/todoApi";
import TodoItemRow from "./TodoItemRow";

const TodoItemList = () => {
    const todoItems = useListTodoItemsQuery()

    return (
        <ul>
            {todoItems.data?.map(item => <TodoItemRow item={item}/>)}
        </ul>
    )
}

export default TodoItemList;
