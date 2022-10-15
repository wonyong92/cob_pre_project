import { useEffect, useState } from 'react';
import { Routes, Route, useNavigate } from 'react-router-dom';
import './App.css';
import MyHeader from './components/MyHeader';
import HeaderModal from './components/HeaderModal';
import MyPage from './pages/MyPage/MyPage';
import EditQuestion from './pages/Question/EditQuestion';
import PostQuestion from './pages/Question/PostQuestion';
import QuestionDetail from './pages/Question/QuestionDetail';
import QuestionList from './pages/Question/QuestionList';
import Login from './pages/Sign/Login';
import Logout from './pages/Sign/Logout';
import SignUp from './pages/Sign/SignUp';
import axios from 'axios';
import { useDispatch } from 'react-redux';
import { getLoginCookie } from './lib/cookie';
import { setSignState } from './action/action';
import RequireAuth from './components/RequireAuth';
import SearchResult from './pages/Search/SearchResult';

function App() {
  const dispatch = useDispatch();
  const navigate = useNavigate();
  const [viewModal, setModal] = useState(false);
  const [searchList, setSearchList] = useState([]);
  const [searchCount, setSearchCount] = useState(0);
  const [keyword, setkeyword] = useState('');
  const [lists, setLists] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const func = async () => {
      const res = await axios.get(
        `http://3.39.180.45:56178/DBtest/tokenLogin`,
        {
          headers: { authorization: getLoginCookie() },
        }
      );
      dispatch(setSignState(res.data));
    };
    func();
  }, []);

  useEffect(() => {
    getAllQuestion();
  }, []);
  const getAllQuestion = () => {
    axios
      .get(`http://3.39.180.45:56178/DBtest/findAllPost?page=1&size=-2`)
      .then((res) => {
        setLists(res.data.posts);
        setLoading(false);
      });
  };
  useEffect(() => {
    if (!keyword) {
      return;
    } else {
      getSearchResult(keyword);
    }
  }, [keyword]);
  const getSearchResult = (keyword) => {
    axios
      .get(`http://3.39.180.45:56178/DBtest/search?phrase=${keyword}`)
      .then((res) => {
        setSearchList(res.data.posts);
        setSearchCount(res.data.questions);
        navigate('/search');
        setLoading(false);
      })
      .catch(() => {});
  };
  const getKeyword = (word) => {
    setkeyword(word);
  };
  if (loading) return null;
  return (
    <div className="App">
      <MyHeader
        viewModal={viewModal}
        setModal={setModal}
        keyword={keyword}
        setkeyword={setkeyword}
        handleKeyword={getKeyword}
      />
      {viewModal ? (
        <HeaderModal viewModal={viewModal} setModal={setModal} />
      ) : null}
      <Routes>
        <Route
          path="/search"
          element={
            <SearchResult searchList={searchList} searchCount={searchCount} />
          }
        />
        <Route path="/" element={<QuestionList lists={lists} />} />
        <Route
          path="/login"
          element={
            <RequireAuth option={false}>
              <Login />
            </RequireAuth>
          }
        />
        <Route
          path="/logout"
          element={
            <RequireAuth option={true}>
              <Logout />
            </RequireAuth>
          }
        />
        <Route
          path="/signup"
          element={
            <RequireAuth option={false}>
              <SignUp />
            </RequireAuth>
          }
        />
        <Route
          path="/mypage"
          element={
            <RequireAuth option={true}>
              <MyPage />
            </RequireAuth>
          }
        />
        <Route
          path="/questiondetail/:id"
          element={<QuestionDetail getAllPost={getAllQuestion} />}
        />
        <Route
          path="/editquestion/:id"
          element={
            <RequireAuth option={true}>
              <EditQuestion />
            </RequireAuth>
          }
        />
        <Route
          path="/questionwrite"
          element={
            <RequireAuth option={true}>
              <PostQuestion />
            </RequireAuth>
          }
        />
      </Routes>
    </div>
  );
}

export default App;
