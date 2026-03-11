import { useEffect, useState } from 'react'
import { getAllHabits } from './Services'
import TopBar from './TopBar'
import SideBar from './SideBar'
import AddHabit from './AddHabit'

function App() {
  const [habits, setHabits] = useState([]);
  const [selectedId, setSelectedId] = useState('add');
  const [loading, setLoading] = useState(true);

  // get all existing habits upon mount
  useEffect(() => {
    getAllHabits()
      .then(({ data }) => setHabits(data))
      .finally(() => setLoading(false))
  }, []);

  const selectedHabit = habits.find(h => h.id === selectedId);

  const handleAdd = (habit) => {
    setHabits(prev => [...prev, habit]);
  }

  const renderPage = () => {
    if (selectedId === 'add') {
      return <AddHabit onAdd={handleAdd} onSelect={setSelectedId} />
    }
  }

  return (
    <div>
      {/* Top Bar */}
      <div>
        <TopBar />
      </div>

      {/* Side Bar */}
      <div>
        <SideBar
          habits={habits}
          onSelect={setSelectedId}
        />
      </div>

      <div className='flex justify-center h-screen bg-blue-950'>
        {renderPage()}
      </div>
    </div>
  )

}

export default App
