import { useEffect, useState } from 'react'
import { habitsUpdate } from './Services'
import TopBar from './TopBar'
import SideBar from './SideBar'
import AddHabit from './AddHabit'
import Welcome from './Welcome'
import HabitScreen from './HabitScreen'
import Login from './Login'

function App() {
  const [habits, setHabits] = useState([]);
  const [selectedId, setSelectedId] = useState(null);
  const [authenticated, setAuthenticated] = useState(false);
  const [user, setUser] = useState(null);

  useEffect(() => {
    fetch('http://localhost:8080/user', {credentials: 'include'})
      .then(res => {
        if (res.ok) {
          return res.json();
        }
        else {
          setAuthenticated(false);
        }
      })
      .then(userData => {
        if (userData) {
          setUser(userData);
          setAuthenticated(true);
          return habitsUpdate().then(({ data }) => setHabits(data));
        }
      })
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
    if (!authenticated)
      return <Login/>
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
      {authenticated && <TopBar user={user}/>}
      {/* Side Bar */}
      {authenticated && <SideBar habits={habits} onSelect={setSelectedId} />}
      <div className={authenticated ? 'flex justify-center h-screen bg-zinc-950 pt-14 ml-20' : ''}>
        {renderPage()}
      </div>
    </div>
  )
}

export default App
