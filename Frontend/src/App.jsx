import { useEffect, useState } from 'react'
import { getAllHabits, habitsUpdate } from './Services'
import TopBar from './TopBar'
import SideBar from './SideBar'
import AddHabit from './AddHabit'
import Welcome from './Welcome'
import HabitScreen from './HabitScreen'

function App() {
  const [habits, setHabits] = useState([]);
  const [selectedId, setSelectedId] = useState(null);
  const [loading, setLoading] = useState(true);

  // get all existing habits upon mount
  useEffect(() => {
    habitsUpdate()
      .then(({ data }) => setHabits(data))
      .finally(() => setLoading(false))
  }, []);

  const selectedHabit = habits.find(h => h.id === selectedId) || null;

  const handleAdd = (habit) => {
    setHabits(prev => [...prev, habit]);
  }

  const handleUpdate = (updated) => {
    setHabits(prev => prev.map(h => h.id === updated.id ? updated : h));
  }

  const handleDelete = (id) => {
    setHabits(prev => prev.filter(h => h.id !== id));
    setSelectedId(null);
  }

  const renderPage = () => {
    if (selectedId === 'add') {
      return <AddHabit onAdd={handleAdd} onSelect={setSelectedId} />
    }
    if (selectedHabit) {
      return <HabitScreen selectedHabit={selectedHabit} onUpdate={handleUpdate} onDelete={handleDelete} />
    }
    return <Welcome habits={habits} />
  }

  return (
    <div>
      {/* Top Bar */}
      <TopBar />
      {/* Side Bar */}
      <SideBar habits={habits} onSelect={setSelectedId} />
      <div className='flex justify-center h-screen bg-blue-950 pt-16 ml-20'>
        {renderPage()}
      </div>
    </div>
  )

}

export default App
