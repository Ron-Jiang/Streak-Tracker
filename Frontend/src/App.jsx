import { useState } from 'react'
import './css/App.css'

function App() {
  const [count, setCount] = useState(0)

  const increment = () => {
    setCount(count+1);
  }

  const decrement = () => {
    setCount(count-1);
  }

  return (
    <div>
      <p>{count}</p>
      <button onClick={increment}>Click to increase</button>
      <button onClick={decrement}>Click to decrease</button>
    </div>   
  )
}

export default App
