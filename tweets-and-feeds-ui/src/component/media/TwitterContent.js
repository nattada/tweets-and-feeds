import { useState, useEffect } from 'react';
import TweetList from "../notifications/TweetList";

function TweeterContent() {
    const [isLoading, setIsLoading] = useState(true);
    const [loadedTweets, setLoadedTweets] = useState([]);
  
    useEffect(() => {
      setIsLoading(true);
      fetch(
        '/twitter/seachTweets?text=something',
        {
            method: 'GET',
            headers: {
              'Content-Type': 'application/json',
              'Authorization':''
            },
          }
      )
        .then((response) => {
          return response.json();
        })
        .then((data) => {
          const tweets = [];
          for (const key in data) {
            const item = {
              id: key,
              ...data[key]
            };
            tweets.push(item);
          }
          setIsLoading(false);
          setLoadedTweets(tweets);
        });
    }, []);
  
    if (isLoading) {
      return (
        <section>
          <p>Loading...</p>
        </section>
      );
    }
  
  return (
    <section>
      <TweetList allTweets={loadedTweets} />
    </section>
  );
}

export default TweeterContent;
