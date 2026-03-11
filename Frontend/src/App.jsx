import { useEffect, useState } from 'react'
import { getAllHabits } from './Services'
import TopBar from './TopBar'

function App() {
  const [habits, setHabits] = useState([])
  const [loading, setLoading] = useState(true)

  // get all existing habits upon mount
  useState(() => {
    getAllHabits()
      .then(({ response }) => setHabits(response))
      .finally(() => setLoading(false))
  }, []);


  return (
    <div>
      {/* Top Bar */}
      <div>
        <TopBar />
      </div>
      <div className='flex justify-center'>
        <p>Hello?</p>
        {loading ? <p>Load</p> : <p>No Load</p>}
      </div>
    </div>
  )

}

export default App
