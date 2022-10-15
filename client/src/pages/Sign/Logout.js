import { useNavigate } from 'react-router-dom';
import styled from 'styled-components';
import MyButton from '../../components/MyButton';
import { deleteCookie, getLoginCookie } from '../../lib/cookie';
import { useDispatch } from 'react-redux';
import { trySignout } from '../../action/action';
import axios from 'axios';
const Logout = () => {
  const navigate = useNavigate();
  const dispatch = useDispatch();
  const onSignOut = () => {
    axios
      .get(`http://3.39.180.45:56178/DBtest/Logout`, {
        headers: { Authorization: getLoginCookie() },
      })
      .then(() => {
        // console.log(res.data);
        deleteCookie();
        dispatch(trySignout());
        localStorage.removeItem('token');
        localStorage.removeItem('userData');
        navigate('/');
      });
  };

  return (
    <Container>
      <div className="description">
        Clicking “Log out” will log you out of our website
      </div>
      <LogoutBox>
        <BtnContainer>
          <div className="title">
            <div>Log out</div>
          </div>
          <div className="btnWrapper">
            <MyButton text={'Log out'} type={'blue'} onClick={onSignOut} />
            <MyButton
              text={'Cancel'}
              type={'skyblue'}
              onClick={() => {
                navigate('/');
              }}
            />
          </div>
        </BtnContainer>
        <Pcontainer>
          <p>
            If you’re on a shared computer, remember to log out of your Open ID
            provider (Facebook, Google, Stack Exchange, etc.) as well.
          </p>
        </Pcontainer>
      </LogoutBox>
    </Container>
  );
};

export default Logout;

const Container = styled.div`
  background-color: #f1f2f3;
  height: 100vh;
  display: flex;
  min-width: auto;
  width: 100%;
  justify-content: center;
  align-items: center;
  flex-direction: column;
  .description {
    margin-bottom: 20px;
    font-size: 20px;
  }
`;
const LogoutBox = styled.div`
  display: flex;
  flex-direction: column;
  background-color: white;
  justify-content: center;
  align-items: center;
  padding: 30px;
  width: 300px;
  height: 280px;
  border-radius: 10px;
  box-shadow: rgba(100, 100, 111, 0.2) 0px 7px 29px 0px;
`;
const BtnContainer = styled.div`
  display: flex;
  flex-direction: column;
  width: 270px;
  height: 250px;
  justify-content: space-between;
  align-items: flex-start;
  margin-left: 20px;
  .title {
    padding-bottom: 10px;
    border-bottom: 1px solid lightgray;
    width: 250px;
    font-size: 20px;
    font-weight: 500;
  }
  .btnWrapper {
    margin-top: 25px;
    button {
      width: 70px;
      height: 38px;
      margin: 5px;
      font-size: 13px;
    }
    button:nth-of-type(2) {
      background-color: white;
      border: none;
    }
    button:nth-of-type(2):hover {
      background-color: #f1f2f3;
    }
  }
`;
const Pcontainer = styled.div`
  width: 400px;
  display: flex;
  width: 270px;
  justify-content: center;
  p {
    margin-top: 40px;
    padding: 0px 20px;
    color: #7a8189;
    font-size: 12px;
  }
`;
