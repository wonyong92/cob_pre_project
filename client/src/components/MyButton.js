/* eslint-disable react/prop-types */

const MyButton = ({ text, type, onClick }) => {
  const btnType = [
    'orange',
    'blue',
    'skyblue',
    'read',
    'selected',
    'default',
  ].includes(type)
    ? type
    : 'default';
  return (
    <button
      className={['MyButton', `MyButton_${btnType}`].join(' ')}
      onClick={onClick}
    >
      {text}
    </button>
  );
};

MyButton.defaultProps = {
  name: 'default',
};

export default MyButton;
