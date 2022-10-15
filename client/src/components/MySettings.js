import { useState } from 'react';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faAngleLeft, faAngleDown } from '@fortawesome/free-solid-svg-icons';
import styled from 'styled-components';
import EditProfile from './EditProfile';
import ChangePassWord from './ChangePassword';
import DeleteProfile from './DeleteProfile';
/* eslint-disable react/prop-types */
import axios from 'axios';
axios.defaults.withCredentials = false;
const MySettings = ({
  setIsProfile,
  nickname,
  setNickname,
  location,
  setLocation,
  about,
  setAbout,
  userId,
}) => {
  const [isToggle1, setIsToggle1] = useState(false);
  const [isToggle2, setIsToggle2] = useState(false);
  const [isToggle3, setIsToggle3] = useState(false);
  return (
    <Container>
      <Wrapper>
        <div className="titleWrapper">
          <div className="title">Edit your profile</div>
          <FontAwesomeIcon
            icon={isToggle1 ? faAngleLeft : faAngleDown}
            className="toggle"
            onClick={() => {
              setIsToggle1(!isToggle1);
            }}
          />
        </div>
        {isToggle1 ? (
          <div></div>
        ) : (
          <EditProfile
            setIsProfile={setIsProfile}
            nickname={nickname}
            setNickname={setNickname}
            location={location}
            setLocation={setLocation}
            about={about}
            setAbout={setAbout}
            userId={userId}
          />
        )}
      </Wrapper>
      <Wrapper>
        <div className="titleWrapper">
          <div className="title">Change Password</div>
          <FontAwesomeIcon
            icon={isToggle2 ? faAngleLeft : faAngleDown}
            className="toggle"
            onClick={() => {
              setIsToggle2(!isToggle2);
            }}
          />
        </div>
        {isToggle2 ? <div></div> : <ChangePassWord userId={userId} />}
      </Wrapper>
      <Wrapper>
        <div className="titleWrapper">
          <div className="title">Delete Profile</div>
          <FontAwesomeIcon
            icon={isToggle3 ? faAngleLeft : faAngleDown}
            className="toggle"
            onClick={() => {
              setIsToggle3(!isToggle3);
            }}
          />
        </div>
        {isToggle3 ? <div></div> : <DeleteProfile userId={userId} />}
      </Wrapper>
    </Container>
  );
};

export default MySettings;

const Container = styled.div`
  display: flex;
  flex-direction: column;
  width: 1070px;
  margin-top: 30px;

  .title {
    font-size: 25px;
    font-weight: 600;
    border-bottom: 1px solid lightgray;
    padding-bottom: 5px;
  }
  .container {
    display: flex;
    flex-direction: row;
    border: 1px solid lightgray;
    border-radius: 5px;
  }
`;
const Wrapper = styled.div`
  display: flex;
  flex-direction: column;
  margin-bottom: 20px;
  .title {
    width: 1040px;
  }
  .box {
    border: 1px solid lightgray;
    border-radius: 5px;
    min-height: 100px;
    margin-top: 10px;
    padding-top: 20px;
    padding-left: 20px;
  }
  .titleWrapper {
    display: flex;
    justify-content: space-between;
    align-items: center;
  }
  .toggle {
    font-size: 25px;
  }
`;
