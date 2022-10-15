import { useState } from 'react';
import styled from 'styled-components';
import MyButton from './MyButton';
import MyProfileImg from './MyProfileImg';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faCheck } from '@fortawesome/free-solid-svg-icons';
import axios from 'axios';
import { getLoginCookie } from '../lib/cookie';
/* eslint-disable react/prop-types */
const EditProfile = ({
  setIsProfile,
  nickname,
  setNickname,
  location,
  setLocation,
  about,
  setAbout,
  userId,
}) => {
  const [isSaved, setIsSaved] = useState(false);
  const onChangeNickname = (e) => {
    setNickname(e.target.value);
  };
  const onChangeLocation = (e) => {
    setLocation(e.target.value);
  };
  const onChangeAbout = (e) => {
    setAbout(e.target.value);
  };

  const onEditProfile = () => {
    axios
      .put(
        `http://3.39.180.45:56178/DBtest/updateProfile?profile_id=${userId}`,
        {
          displayname: nickname,
          location: location,
          about: about,
        },
        { headers: { Authorization: getLoginCookie() } }
      )
      .then(() => {
        setIsSaved(!isSaved);
      });
  };
  return (
    <Container>
      <MyProfileImg userId={userId} />
      <InputContainer>
        <h4>Display name</h4>
        <input value={nickname} required onChange={onChangeNickname}></input>
        <h4>Location</h4>
        <input value={location} onChange={onChangeLocation}></input>
        <h4>About me</h4>
        <textarea value={about} onChange={onChangeAbout}></textarea>
        <div className="aboutWrapper">
          <p className="about">{about}</p>
        </div>
      </InputContainer>
      <BtnContainer>
        {isSaved ? (
          <div className="msgContainer">
            <FontAwesomeIcon icon={faCheck} className="check" />
            <h4>Your profile has been saved successfully.</h4>
          </div>
        ) : null}
        <div className="btnwrapper">
          <MyButton
            text={'Save profile'}
            onClick={() => {
              onEditProfile();
            }}
            type={'blue'}
          />
          <MyButton
            text={'Cancel'}
            onClick={() => {
              setIsProfile(true);
            }}
          />
        </div>
      </BtnContainer>
    </Container>
  );
};
export default EditProfile;

const Container = styled.h3`
  display: flex;
  flex-direction: column;
  margin-top: 20px;
  margin-bottom: 10px;
  padding-top: 20px;
  padding-left: 20px;
  border: 1px solid #d0d4d7;
  border-radius: 5px;
  max-width: 1040px;
`;

const InputContainer = styled.div`
  display: flex;
  flex-direction: column;
  margin-top: 20px;
  input {
    margin-top: 5px;
    margin-bottom: 10px;
    height: 35px;
    width: 420px;
    font-size: 14px;
    color: #363b3f;
    text-indent: 10px;
    border: 1px solid #d5d7d9;
    border-radius: 3px;
  }
  input:focus {
    border: 1px solid cornflowerblue;
    border-radius: 2px;
    outline: none;
    box-shadow: 0 0 0 3px #cde9fe;
  }
  textarea {
    width: 750px;
    min-height: 150px;
    padding: 10px;
    border: 1px solid #d5d7d9;
    border-radius: 3px;

    font-size: 14px;
  }
  textarea:focus {
    border: 1px solid cornflowerblue;
    border-radius: 2px;
    outline: none;
    box-shadow: 0 0 0 3px #cde9fe;
  }
  .aboutWrapper {
    width: 71%;
  }
  .about {
    margin-top: 10px;
    margin-left: 10px;
    font-weight: 400;
    font-size: 14px;
    display: block;
    word-wrap: break-word;
  }
`;
const BtnContainer = styled.div`
  display: flex;
  flex-direction: column;
  margin-top: 40px;
  margin-bottom: 20px;
  .msgContainer {
    display: flex;
    flex-direction: column;
    height: 140px;
    width: 840px;
    border: 1px solid green;
    background-color: #edf8f0;
    align-items: center;
    justify-content: center;
  }
  .btnwrapper {
    display: flex;
    width: 210px;
    justify-content: space-between;
    margin-top: 30px;
  }
  button {
    width: 100px;
    height: 40px;
    padding: 10px 10px;
  }
  button:nth-of-type(2) {
    color: #0289fe;
    border: none;
  }
`;
