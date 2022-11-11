import {useListTodoItemsQuery} from "../api/todoApi";
import TodoItemRow from "./TodoItemRow";
import AddTodoItemRow from "./AddTodoItemRow";

const TodoItemList = () => {
    const todoItems = useListTodoItemsQuery()

    return (
        <ul>
            {todoItems.data?.map(item => <TodoItemRow item={item}/>)}
            <AddTodoItemRow/>
        </ul>
    )
}

export default TodoItemList;
