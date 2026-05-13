import { useState } from "react";
import "./App.css";
import axios from "axios";

function App() {

  const [message, setMessage] = useState("");

  const [messages, setMessages] = useState([]);

  async function handleSendMessage() {

    if (message.trim() === "") {
      return;
    }

    const userMessage = {
      id: Date.now(),
      text: message,
      sender: "user"
    };

    setMessages((prevMessages) => [
      ...prevMessages,
      userMessage
    ]);

    try {

      const response = await axios.post(
        "http://localhost:8080/chat",
        {
          message: message
        }
      );

      const botMessage = {
        id: Date.now() + 1,
        text: response.data.response,
        sender: "bot"
      };

      setMessages((prevMessages) => [
        ...prevMessages,
        botMessage
      ]);

    } catch (error) {

      const errorMessage = {
        id: Date.now() + 1,
        text: "Erro ao conectar com o servidor.",
        sender: "bot"
      };

      setMessages((prevMessages) => [
        ...prevMessages,
        errorMessage
      ]);
    }

    setMessage("");
  }

  return (
    <div className="app-container">

      <div className="chat-container">

        <h1 className="chat-title">
          Support Chatbot
        </h1>

        <div className="messages-container">

          {messages.map((msg) => (
            <div
              key={msg.id}
              className={
                msg.sender === "user"
                  ? "message user-message"
                  : "message bot-message"
              }
            >
              {msg.text}
            </div>
          ))}

        </div>

        <div className="input-container">

          <input
            type="text"
            placeholder="Digite uma mensagem"
            value={message}
            onChange={(e) => setMessage(e.target.value)}
            className="chat-input"
          />

          <button
            onClick={handleSendMessage}
            className="send-button"
          >
            Enviar
          </button>

        </div>

      </div>

    </div>
  );
}

export default App;