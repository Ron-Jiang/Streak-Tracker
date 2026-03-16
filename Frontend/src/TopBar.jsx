import {logout} from './Services'

function TopBar({user}) {
    return (
        <>
            <div className='bg-amber-300 h-16 fixed top-0 left-0 right-0 z-10'>
                <div className='flex items-center justify-between'>
                    <p className='ml-5'>THE STREAK APP</p>
                    {user?.avatar_url ? (
                        <img src={user.avatar_url} alt="user's GitHub avatar" className='m-3 mr-5 h-10 w-10 rounded-full' />
                    ) :(
                        <div className='m-3 mr-5 flex h-10 w-10 items-center justify-center rounded-4xl bg-blue-400'></div>
                    )}
                    <button onClick={logout} className='cursor-pointer border-2 border-amber-600'>Log Out</button>
                </div>
            </div>
        </>
    )
}
export default TopBar