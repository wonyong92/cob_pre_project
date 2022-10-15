import axios from 'axios';
import { useState } from 'react';
import styled from 'styled-components';
import { getLoginCookie } from '../lib/cookie';
/* eslint-disable react/prop-types */
const DeleteProfile = ({ userId }) => {
  const [isChecked, setIsChecked] = useState(false);
  const [isDeleted, setIsDeleted] = useState(false);
  const onDeleteAccount = () => {
    axios.delete(
      `http://3.39.180.45:56178/DBtest/deleteMember?member_id=${userId}`,
      {
        headers: { Authorization: getLoginCookie() },
      }
    );
  };
  return (
    <Container>
      {isDeleted ? (
        <div className="deleteMsg">
          <h2>Profile deleted</h2>
          <p>
            Your profile has been successfully deletedan you are now logged out.
          </p>
        </div>
      ) : (
        <>
          <div className="info">
            <p>
              Before confirming that you would like your profile deleted,
              we&apos;d like to take a moment to explain the implications of
              deletion:
            </p>
            <li>
              Deletion is irreversible, and you will have no way to regain any
              of your original content, should this deletion be carried out and
              you change your mind later on.
            </li>
            <li>
              Your questions and answers will remain on the site, but will be
              disassociated and anonymized (the author will be listed as
              &ldquo;user19799039&ldquo;) and will not indicate your authorship
              even if you later return to the site.
            </li>
            <p>
              Confirming deletion will only delete your profile on Stack
              Overflow - it will not affect any of your other profiles on the
              Stack Exchange network. If you want to delete multiple profiles,
              you&apos;ll need to visit each site separately and request
              deletion of those individual profiles.
            </p>
          </div>
          <div className="checkbox">
            <input
              type="checkbox"
              onClick={() => {
                setIsChecked(!isChecked);
              }}
            />
            <p>
              I have read the information stated above and understand the
              implications of having my profile deleted. I wish to proceed with
              the deletion of my profile.
            </p>
          </div>
          <div className="btnWrapper">
            <button
              className={isChecked ? 'active' : ''}
              disabled={isChecked ? false : true}
              onClick={() => {
                onDeleteAccount();
                setIsDeleted(!isDeleted);
              }}
            >
              Delete profile
            </button>
          </div>
        </>
      )}
    </Container>
  );
};
export default DeleteProfile;
const Container = styled.div`
  display: flex;
  flex-direction: column;
  margin-top: 20px;
  margin-bottom: 30px;
  padding-top: 20px;
  padding-left: 20px;
  border: 1px solid #d0d4d7;
  border-radius: 5px;
  width: 1040px;
  .info p {
    margin-top: 20px;
    margin-bottom: 10px;
  }
  li:first-of-type {
    margin-bottom: 10px;
  }
  li {
    margin-left: 20px;
  }
  .checkbox {
    display: flex;
  }
  .checkbox input {
    margin: 10px;
  }
  button {
    margin-top: 20px;
    height: 40px;
    width: 110px;
    background-color: #eaa09f;
    color: white;
    border: none;
    border-radius: 5px;
    margin-bottom: 30px;
  }
  .active {
    background-color: #c22e32;
    cursor: pointer;
  }
  .active:hover {
    background-color: #a32327;
  }
  .deleteMsg {
    display: flex;
    flex-direction: column;
    padding-top: 20px;
    margin-left: 20px;
    margin-bottom: 30px;
    height: 120px;
    p {
      margin-top: 20px;
    }
  }
`;
