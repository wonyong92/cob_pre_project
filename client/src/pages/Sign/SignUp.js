import { Link, useNavigate } from 'react-router-dom';
import { useState } from 'react';
import MyButton from '../../components/MyButton';
import axios from 'axios';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import {
  Container,
  SignupContainer,
  SignupBox,
} from '../../components/SignUpStyle';
import {
  faQuestionCircle,
  faCheckToSlot,
  faBookmark,
  faTrophy,
} from '@fortawesome/free-solid-svg-icons';
import { getLoginCookie } from '../../lib/cookie';

const SignUp = () => {
  const [userId, setUserId] = useState('');
  const [userPw, setuserPw] = useState('');
  const [userRePw, setUserRePw] = useState('');
  const [checkIdMsg, setIdMsg] = useState();
  const [checkPwMsg, setPwMsg] = useState();
  const [checkRePwMsg, setRePwMsg] = useState();
  const navigate = useNavigate();
  // id 정규표현식
  const isValidId = (str) => {
    let idRegExp = '^[A-Za-z0-9]{6,12}$';
    let regExp = new RegExp(idRegExp);
    return regExp.test(str);
  };
  // pw 정규표현식
  const isValidPw = (str) => {
    let pwRegExp =
      '^(?=.*[a-zA-z])(?=.*[0-9])(?=.*[$`~!@$!%*#^?&\\(\\)-_=+]).{8,16}$';
    let regExp = new RegExp(pwRegExp);
    return regExp.test(str);
  };
  // id 유효성검사
  const getUserID = (e) => {
    setUserId(e.target.value);
    isValidId(userId)
      ? setIdMsg(<p>중복 확인을 위해 ID check 버튼을 눌러주세요</p>)
      : setIdMsg(<p>6~12자 사이의 영문과 숫자를 입력하세요</p>);
  };
  // 중복 id 체크
  const checkUserID = async () => {
    try {
      if (isValidId(userId) && userId !== '') {
        const res = await axios.get(
          `http://3.39.180.45:56178/DBtest/checkExistId?id=${userId}`,
          { headers: { Authorization: getLoginCookie() } }
        );
        const data = res.data;
        if (data) {
          setIdMsg(<p>사용가능한 아이디입니다</p>);
        } else {
          setIdMsg(<p>중복된 아이디입니다</p>);
        }
      } else {
        setIdMsg(<p>아이디 조건을 만족해주세요</p>);
      }
    } catch (err) {
      if (err) setIdMsg(<p>아이디 조건을 만족해주세요</p>);
    }
  };
  // pw 유효성검사
  const getUserPW = (e) => {
    setuserPw(e.target.value);
    if (isValidPw(e.target.value)) {
      setPwMsg(<p>비밀번호가 조건에 만족합니다</p>);
    } else {
      setPwMsg(<p>8~16자 사이의 영문, 숫자, 특수문자를 조합하세요</p>);
    }
  };
  // rePw 유효성검사
  const getUserRePW = (e) => {
    setUserRePw(e.target.value);
    if (userPw === e.target.value) {
      setRePwMsg(<p>비밀번호가 일치합니다</p>);
    } else {
      setRePwMsg(<p>비밀번호가 일치하지 않습니다</p>);
    }
  };
  const onSignUp = () => {
    if (userPw !== userRePw || !isValidPw(userPw)) return;
    if (userPw === userRePw) {
      axios
        .post(
          'http://3.39.180.45:56178/DBtest/createMember',
          {
            id: userId,
            password: userRePw,
          },
          { headers: { Authorization: getLoginCookie() } }
        )
        .then(() => {
          console.log('sign up suceed');
          navigate('/login');
        });
    }
  };
  return (
    <Container>
      <div className="msgContainer">
        <p className="title">Join the Stack Overflow community</p>
        <div className="msgBox">
          <div>
            <FontAwesomeIcon icon={faQuestionCircle} className="icon" />
            <span className="sentence">Get unstuck — ask a question</span>
          </div>
          <div>
            <FontAwesomeIcon icon={faCheckToSlot} className="icon" />
            <span className="sentence">
              Unlock new spanrivileges like voting and commenting
            </span>
          </div>
          <div>
            <FontAwesomeIcon icon={faBookmark} className="icon" />
            <span className="sentence">
              Save your favorite tags, filters, and jobs
            </span>
          </div>
          <div>
            <FontAwesomeIcon icon={faTrophy} className="icon" />
            <span className="sentence">Earn reputation and badges</span>
          </div>
        </div>
        <p className="footer">
          Collaborate and share knowledge with a private group for FREE.
          <br />
          Get Stack Overflow for Teams free for up to 50 users.
        </p>
      </div>
      <SignupContainer>
        <div className="btnWrapper">
          <MyButton
            text={'Sign up with Google'}
            type={'default'}
            onClick={() => {}}
          />
        </div>
        <SignupBox>
          <>
            <span>Id</span>
            <input type="text" onChange={getUserID}></input>
            <MyButton text={'ID check'} onClick={checkUserID} type={'orange'} />
            {checkIdMsg}
          </>
          <>
            <span>Password</span>
            <input type="password" onChange={getUserPW}></input>
            {checkPwMsg}
          </>
          <>
            <span>Password (again)</span>
            <input type="password" onChange={getUserRePW}></input>
            {checkRePwMsg}
          </>
          <MyButton text={'Sign up'} type={'blue'} onClick={onSignUp} />
          <p>
            By clicking “Sign up”, you agree to our terms of service, privacy
            policy and cookie policy
          </p>
        </SignupBox>
        <div className="loginWrapper">
          <p>
            Already have an account?
            <Link to="/login">
              <span className="loginbtn">Log in</span>
            </Link>
          </p>
        </div>
      </SignupContainer>
    </Container>
  );
};
export default SignUp;
