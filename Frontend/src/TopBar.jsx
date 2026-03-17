import {logout} from './Services'

function TopBar({user, onSelect}) {
    return (
        <>
            <div className='flex items-center justify-between px-6 h-14 bg-zinc-950 border-b border-zinc-800 fixed top-0 left-0 right-0 z-10'>
                <span className='text-amber-500 font-bold text-lg'>THE STREAK APP</span>
                <button onClick={() => onSelect('leaderboard')} className='text-zinc-400 hover:text-amber-500 transition-colors cursor-pointer'>
                    Leaderboard
                </button>
                <div className='flex items-center gap-3'>
                    {user?.avatar_url ? (
                        <img src={user.avatar_url} alt="user's GitHub avatar" className='h-10 w-10 rounded-full border border-zinc-700' />
                    ) :(
                        <div className='h-10 w-10 flex items-center rounded-full bg-zinc-700 border border-zinc-300'></div>
                    )}
                    <button onClick={logout} className='text-zinc-500 text-xs tracking-wider hover:text-white  transition-colors cursor-pointer'>Log Out</button>
                </div>
            </div>
        </>
    )
}
export default TopBar