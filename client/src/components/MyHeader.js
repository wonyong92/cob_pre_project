import styled from 'styled-components';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import {
  faBars,
  faMagnifyingGlass,
  faInbox,
  faTrophy,
  faCircleQuestion,
} from '@fortawesome/free-solid-svg-icons';
import { faStackOverflow } from '@fortawesome/fontawesome-free-brands';
import MyButton from './MyButton';
import { useNavigate, Link } from 'react-router-dom';
import { useSelector } from 'react-redux';
import SearchQuestion from './SearchQuestion';
/* eslint-disable react/prop-types */
import RequireAuth from './RequireAuth';
const MyHeader = ({
  viewModal,
  setModal,
  keyword,
  setkeyword,
  handleKeyword,
}) => {
  const state = useSelector((state) => state.signInReducer);
  // const userData = JSON.parse(localStorage.getItem('userData'));
  // let memberId = parseInt(userData.memberId);
  // let userId = state.loginState.userid;

  const navigate = useNavigate();
  const viewLogout = () => {
    setModal(!viewModal);
  };
  const handleKeywordKeyword = (word) => {
    handleKeyword(word);
  };

  return (
    <Container>
      <Wrapper>
        {state.loginState ? (
          <RequireAuth option={true}>
            <>
              <button className="logoWrapper" onClick={() => navigate('/')}>
                <FontAwesomeIcon icon={faStackOverflow} className="logo" />
                <span>stack overflow</span>
              </button>
              <div className="deactivemenu">
                <span>Products</span>
              </div>
              <div className="searchbar">
                <FontAwesomeIcon
                  icon={faMagnifyingGlass}
                  className="magnifying"
                />
                <SearchQuestion
                  keyword={keyword}
                  setkeyword={setkeyword}
                  handleKeywordKeyword={handleKeywordKeyword}
                />
              </div>
              <div className="loginIconWrapper">
                <Link
                  to="/mypage"
                  style={{ textDecoration: 'none' }}
                  className="imgWrapper"
                >
                  {/* <img
                    src={`http://3.39.180.45:56178/DBtest/download?memberId=${memberId}`}
                    alt="profile"
                  ></img>
                  <span className="loginMypage">{userId}</span> */}
                  mypage
                </Link>
                <FontAwesomeIcon icon={faInbox} className="icon" />
                <FontAwesomeIcon icon={faTrophy} className="icon" />
                <FontAwesomeIcon icon={faCircleQuestion} className="icon" />
                <FontAwesomeIcon
                  icon={faBars}
                  className="icon"
                  onClick={viewLogout}
                />
              </div>
            </>
          </RequireAuth>
        ) : (
          <RequireAuth option={false}>
            <>
              <button className="logoWrapper" onClick={() => navigate('/')}>
                <FontAwesomeIcon icon={faStackOverflow} className="logo" />
                <span>
                  stack<strong>overflow</strong>
                </span>
              </button>
              <div className="logout-deactivemenu">
                <span>About</span>
                <span>Products</span>
                <span>For Teams</span>
              </div>
              <div className="searchbar">
                <FontAwesomeIcon
                  icon={faMagnifyingGlass}
                  className="magnifying"
                />
                <SearchQuestion
                  keyword={keyword}
                  setkeyword={setkeyword}
                  handleKeywordKeyword={handleKeywordKeyword}
                />
              </div>
              <BtnWapper>
                <MyButton
                  text={`Log in`}
                  type={'skyblue'}
                  onClick={() => navigate('/login')}
                />
                <MyButton
                  text={`Sign up`}
                  type={'blue'}
                  onClick={() => navigate('/signup')}
                />
              </BtnWapper>
            </>
          </RequireAuth>
        )}
      </Wrapper>
    </Container>
  );
};
const Container = styled.header`
  background-color: #f8f9f9;
  border-top: solid 3px #f2740d;
  box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.1);
  display: flex;
  justify-content: center;
  height: 50px;
  min-width: auto;
  width: 100%;
`;
const Wrapper = styled.div`
  max-width: 100%;
  width: 1330px;
  height: 50px;
  display: flex;
  align-items: center;
  height: 100%;
  margin: 0 auto;
  .logoWrapper {
    width: 165px;
    padding: 8px;
    background-color: transparent;
    border: none;
    color: black;
    display: flex;
    justify-content: center;
    cursor: pointer;
    &:hover {
      background-color: #e3e6e8;
    }
    .logo {
      font-size: 30px;
    }
    span {
      font-size: 18px;
      margin-top: 3px;
      margin-left: 3px;
      strong {
      }
    }
  }
  .deactivemenu {
    width: 5%;
    padding: 5px 5px;
    border-radius: 20px;
    margin-left: 10px;
    display: flex;
    justify-content: center;
    font-size: 14px;
    color: #818487;

    cursor: pointer;
    &:hover {
      background-color: #e3e6e8;
    }
  }
  .searchbar {
    width: 60%;
    display: flex;
    .magnifying {
      position: relative;
      left: 30px;
      top: 6px;
      color: #8a939b;
      font-size: 16px;
    }
  }
  .loginIconWrapper {
    width: 20%;
    display: flex;
    justify-content: space-between;
    align-items: center;
    margin-left: 10px;
    white-space: nowrap;
    a {
      text-decoration: none;
      color: #0f0f0f;
      font-size: 15px;
      padding: 13px;
    }
    .imgWrapper {
      /* padding: 11px 3px; */
      /* display: flex; */
      align-items: center;
      img {
        height: 25px;
        width: 25px;
        border-radius: 5px;
      }
      span {
        font-size: 12px;
        color: #363b3f;
        white-space: nowrap;
        padding-left: 3px;
        margin-top: 3px;
      }
    }
    .imgWrapper:hover {
      background-color: #e3e6e8;
    }
    .icon {
      padding: 9px;
      height: 30px;
      width: 20px;
      display: flex;
      cursor: pointer;
      color: #232629;
    }
    .icon:hover {
      background-color: #e3e6e8;
    }
  }
  /* 로그아웃상태 */
  .logout-deactivemenu {
    display: flex;
    width: 20%;
    justify-content: space-around;
    font-size: 14px;
    color: #818487;
    border-radius: 20px;
    margin-left: 10px;
    span {
      padding: 15px;
      white-space: nowrap;
      padding: 5px 5px;
      border-radius: 20px;
      cursor: pointer;
    }
    span:hover {
      background-color: #e3e6e8;
    }
  }
`;
const BtnWapper = styled.div`
  display: flex;
  button:first-of-type {
    margin: 0px 10px;
    width: 80px;
  }
  button:nth-of-type(2) {
    width: 80px;
  }
`;
export default MyHeader;
