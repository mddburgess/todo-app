import Container from "react-bootstrap/Container";
import TodoItemList from "./components/TodoItemList";
import AddTodoItemForm from "./components/AddTodoItemForm";
import {Provider} from "react-redux";
import store from "./store";

export const App = () => (
    <Provider store={store}>
        <Container fluid="sm" className="mw-md">
            <h1>My Todos</h1>
            <AddTodoItemForm/>
            <TodoItemList/>
        </Container>
    </Provider>
)
