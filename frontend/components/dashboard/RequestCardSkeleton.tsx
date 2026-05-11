import { LinearGradient } from "expo-linear-gradient"; // ✅ import correto
import React, { useEffect, useRef } from "react";
import { Animated, StyleSheet, View } from "react-native";

export default function RequestCardSkeleton() {
  const shimmerAnim = useRef(new Animated.Value(-1)).current;

  useEffect(() => {
    const loop = Animated.loop(
      Animated.timing(shimmerAnim, {
        toValue: 1,
        duration: 1400,
        useNativeDriver: true,
      })
    );
    loop.start();
    return () => loop.stop();
  }, [shimmerAnim]);

  const translateX = shimmerAnim.interpolate({
    inputRange: [-1, 1],
    outputRange: [-300, 300], // tamanho suficiente para shimmer
  });

  return (
    <View style={styles.container}>
      <Animated.View
        style={[
          styles.shimmerWrapper,
          { transform: [{ translateX }] },
        ]}
      >
        <LinearGradient
          colors={["#E5E7EB", "#F3F4F6", "#E5E7EB"]}
          start={{ x: 0, y: 0 }}
          end={{ x: 1, y: 0 }}
          style={styles.shimmer}
        />
      </Animated.View>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    height: 180,
    borderRadius: 12,
    borderWidth: 1,
    borderColor: "#E5E7EB",
    backgroundColor: "#E5E7EB",
    overflow: "hidden",
    marginVertical: 6,
  },
  shimmerWrapper: {
    ...StyleSheet.absoluteFillObject,
  },
  shimmer: {
    flex: 1,
    width: "100%",
  },
});